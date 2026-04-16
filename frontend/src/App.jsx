import React, { useState, useEffect } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";
import { BrowserRouter as Router, Routes, Route, Link, NavLink } from "react-router-dom";
import { FaCog } from "react-icons/fa";

import Dashboard from "./Dashboard";
import Tasks from "./Tasks";
import Containers from "./Containers";
import Reports from "./Reports";
import NotificationSettings from "./NotificationSettings";
import AlertSettings from "./AlertSettings";

export default function App() {

  const [containers, setContainers] = useState([]);
  const [tasks, setTasks] = useState([]);
  const [completedTasks, setCompletedTasks] = useState([]);
  const [alertSettings, setAlertSettings] = useState({
    warningLevel: 70,
    criticalLevel: 90,
  });

  const [currentTime, setCurrentTime] = useState(new Date());
  const [loading, setLoading] = useState(false);
  const [systemStatus, setSystemStatus] = useState("online");
  const [errorMessage, setErrorMessage] = useState(null);

  // =====================
  // HEADER CLOCK
  // =====================
  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);
    return () => clearInterval(timer);
  }, []);

  const formattedDate = currentTime.toLocaleDateString("fi-FI");
  const formattedTime = currentTime.toLocaleTimeString("fi-FI");

  // =====================
  // AUTO REFRESH CONTAINERS
  // =====================
  useEffect(() => {
    refreshContainers();
    const interval = setInterval(() => {
      refreshContainers();
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  // =====================
  // AUTO REFRESH TASKS
  // =====================
  useEffect(() => {
    refreshTasks();
    const interval = setInterval(() => {
      refreshTasks();
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  // =====================
  // SAFE DATE HELPERS
  // =====================
  const isStale = (timestamp) => {
    if (!timestamp) return true;
    const d = new Date(timestamp);
    if (isNaN(d.getTime())) return true;
    return (Date.now() - d.getTime()) / 60000 > 60;
  };

  const formatTimestamp = (timestamp) => {
    if (!timestamp) return "Ei tietoa";
    const cleaned = String(timestamp).trim();
    const d = new Date(cleaned);
    if (isNaN(d.getTime())) return "Ei tietoa";
    return d.toLocaleString("fi-FI", {
      day: "2-digit",
      month: "2-digit",
      year: "numeric",
      hour: "2-digit",
      minute: "2-digit",
      second: "2-digit",
    });
  };

  // =====================
  // FETCH CONTAINERS
  // =====================
  const refreshContainers = async () => {
    setLoading(true);
    setErrorMessage(null);

    try {
      const [siteRes, binRes] = await Promise.all([
        fetch("http://localhost:8080/api/sites"),
        fetch("http://localhost:8080/api/bins")
      ]);

      if (!siteRes.ok) throw new Error("Sites fetch failed");
      if (!binRes.ok) throw new Error("Bins fetch failed");

      const sites = await siteRes.json();
      const bins = await binRes.json();

      const fromBins = bins.map(bin => {
        const site = sites.find(s => String(s.id) === String(bin.siteId));
        const fill = Number(bin.fillLevel ?? 0);

        return {
          id: String(bin.siteId),
          name: site?.name ?? bin.name ?? `Bin ${bin.binId}`,
          location: site?.location ?? "Ei tietoa",
          capacity: bin.capacityLiters ?? "",
          fillLevel: fill,
          status: bin.status === "CRITICAL" ? "critical" : bin.status === "WARNING" ? "warning" : "normal",
          lastUpdate: formatTimestamp(bin.lastUpdated),
          isStale: isStale(bin.lastUpdated),
          isOnline: true
        };
      });

      const siteIdsWithBins = new Set(bins.map(b => String(b.siteId)));
      const fromSitesOnly = sites
        .filter(s => !siteIdsWithBins.has(String(s.id)))
        .map(s => ({
          id: String(s.id),
          name: s.name,
          location: s.location,
          capacity: "",
          fillLevel: 0,
          status: "normal",
          lastUpdate: "Ei tietoa",
          isStale: true,
          isOnline: false
        }));

      setContainers([...fromBins, ...fromSitesOnly]);
      setSystemStatus("online");

    } catch (err) {
      console.error(err);
      setErrorMessage(err.message);
      setSystemStatus("offline");
    } finally {
      setLoading(false);
    }
  };

  // =====================
  // FETCH TASKS
  // =====================
  const refreshTasks = async () => {
    try {
      const [tasksRes, alertsRes, binsRes, sitesRes] = await Promise.all([
        fetch("http://localhost:8080/api/tasks"),
        fetch("http://localhost:8080/api/alerts"),
        fetch("http://localhost:8080/api/bins"),
        fetch("http://localhost:8080/api/sites"),
      ]);

      if (!tasksRes.ok) throw new Error("Tasks fetch failed");
      if (!alertsRes.ok) throw new Error("Alerts fetch failed");
      if (!binsRes.ok) throw new Error("Bins fetch failed");
      if (!sitesRes.ok) throw new Error("Sites fetch failed");

      const tasksData = await tasksRes.json();
      const alertsData = await alertsRes.json();
      const binsData = await binsRes.json();
      const sitesData = await sitesRes.json();

      const enriched = tasksData.map(task => {
        const alert = alertsData.find(a => a.id === task.alertId) ?? null;
        const bin = alert ? binsData.find(b => Number(b.binId) === Number(alert.binId)) ?? null : null;
        const site = bin ? sitesData.find(s => Number(s.id) === Number(bin.siteId)) ?? null : null;

        return {
          ...task,
          alert,
          binName: site?.name ?? bin?.name ?? `Astia #${alert?.binId ?? "?"}`,
          fillLevel: bin?.fillLevel ?? null,
        };
      });

      setTasks(enriched);
    } catch (err) {
      console.error("Virhe tehtävien haussa:", err);
    }
  };

  return (
    <Router>
      <div className="app">
        <header className="header">

          <div className="position-relative d-flex align-items-center px-3">
            <div className="header-title position-absolute top-50 start-50 translate-middle text-center">
              <h1>Älykäs jäteastioiden seuranta</h1>
              <p>Pilotissa 5 kohdetta</p>
            </div>
            <div className="ms-auto text-end">
              <div>{formattedDate}</div>
              <div>{formattedTime}</div>
            </div>
          </div>

          <nav className="navbar navbar-expand-lg custom-navbar mt-3">
            <div className="container-fluid">
              <div className="navbar-nav justify-content-center w-100">
                <NavLink to="/" className="nav-link mx-3">Tilannekuva</NavLink>
                <NavLink to="/tehtavat" className="nav-link mx-3">Työtehtävät</NavLink>
                <NavLink to="/sailiot" className="nav-link mx-3">Säiliöt</NavLink>
                <NavLink to="/raportit" className="nav-link mx-3">Raportit</NavLink>
              </div>
              <div className="dropdown">
                <button className="btn btn-link nav-link dropdown-toggle" data-bs-toggle="dropdown">
                  <FaCog size={20} />
                </button>
                <ul className="dropdown-menu dropdown-menu-end">
                  <li>
                    <Link className="dropdown-item" to="/asetukset/yhteystiedot">
                      Ilmoitusten vastaanottajat
                    </Link>
                  </li>
                  <li>
                    <Link className="dropdown-item" to="/asetukset/halytysrajat">
                      Hälytysrajat
                    </Link>
                  </li>
                </ul>
              </div>
            </div>
          </nav>

        </header>

        <main className="content">
          <Routes>
            <Route path="/" element={
              <Dashboard
                containers={containers}
                tasks={tasks}
                loading={loading}
                systemStatus={systemStatus}
                errorMessage={errorMessage}
              />
            } />
            <Route path="/sailiot" element={
              <Containers containers={containers} setContainers={setContainers} />
            } />
            <Route path="/tehtavat" element={
              <Tasks tasks={tasks} setTasks={setTasks} />
            } />
            <Route path="/raportit" element={
              <Reports containers={containers} completedTasks={completedTasks} />
            } />
            <Route path="/asetukset/yhteystiedot" element={<NotificationSettings />} />
            <Route path="/asetukset/halytysrajat" element={
              <AlertSettings alertSettings={alertSettings} setAlertSettings={setAlertSettings} />
            } />
          </Routes>
        </main>
      </div>
    </Router>
  );
}