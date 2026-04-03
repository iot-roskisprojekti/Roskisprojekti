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
  // AUTO REFRESH
  // =====================
  useEffect(() => {
    refreshContainers();
    const interval = setInterval(() => {
      refreshContainers();
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  // =====================
  // AUTO TASKS
  // =====================
  useEffect(() => {
    setTasks(prev => {
      const updated = [...prev];
      containers.forEach(c => {
        if (c.fillLevel >= 70) {
          const exists = updated.some(t => t.containerId === c.id);
          if (!exists) {
            updated.push({
              id: "auto-" + c.id,
              containerId: c.id,
              containerName: c.name,
              alertLevel: c.fillLevel,
              priority: c.fillLevel >= 85 ? "Kriittinen" : "Varoitus",
              assignedTo: "Ei osoitettu",
              createdAt: new Date().toISOString()
            });
          }
        }
      });
      return updated;
    });
  }, [containers]);

  const handleCompleteTask = (taskId, containerId) => {
    setContainers(prev =>
      prev.map(c =>
        c.id === String(containerId)
          ? { ...c, fillLevel: 0 }
          : c
      )
    );
    setTasks(prev => {
      const task = prev.find(t => t.id === taskId);
      if (task) {
        setCompletedTasks(old => {
          if (old.some(t => t.id === taskId)) return old;
          return [...old, { ...task, completedAt: new Date().toISOString() }];
        });
      }
      return prev.filter(t => t.id !== taskId);
    });
  };

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

    console.log("formatTimestamp input:", timestamp, "-> Date:", d, "valid:", !isNaN(d.getTime()));

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
  // FETCH DATA
  // =====================
  const refreshContainers = async () => {
    setLoading(true);
    setErrorMessage(null);

    try {
      const siteRes = await fetch("http://localhost:8080/api/sites");
      const measRes = await fetch("http://localhost:8080/api/measurements");

      if (!siteRes.ok) throw new Error("Sites fetch failed");
      if (!measRes.ok) throw new Error("Measurements fetch failed");

      const sites = await siteRes.json();
      const measurements = await measRes.json();

      console.log("RAW measurements:", JSON.stringify(measurements));

      const mapped = sites.map(site => {

        const siteId = String(site.id ?? site.binId);

        const relevant = measurements
          .filter(m => String(m.binId) === siteId)
          .sort((a, b) =>
            new Date(b.measuredAt).getTime() - new Date(a.measuredAt).getTime()
          );

        const m = relevant[0];

        console.log("Site", siteId, "-> latest measurement:", m);

        const BIN_HEIGHT = 100;

        const fill = m?.distance != null
          ? Math.max(0, Math.min(100, ((BIN_HEIGHT - m.distance) / BIN_HEIGHT) * 100))
          : null;

        const timestamp = m?.measuredAt ?? null;

        console.log("Site", siteId, "-> timestamp raw:", timestamp, "-> formatted:", formatTimestamp(timestamp));

        return {
          id: siteId,
          name: site.name,
          location: site.location,
          capacity: site.capacity,
          fillLevel: fill ?? 0,
          status: fill == null
            ? "offline"
            : fill >= 85
              ? "critical"
              : fill >= 70
                ? "warning"
                : "normal",
          lastUpdate: formatTimestamp(timestamp),
          isStale: isStale(timestamp),
          isOnline: !!m
        };
      });

      setContainers(mapped);

    } catch (err) {
      console.error(err);
      setErrorMessage(err.message);
      setSystemStatus("offline");
    } finally {
      setLoading(false);
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

          <nav className="navbar navbar-expand-lg bg-body-tertiary mt-3">
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
              <Tasks tasks={tasks} onCompleteTask={handleCompleteTask} />
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