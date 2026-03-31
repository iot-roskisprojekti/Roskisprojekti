import React, { useState, useEffect } from "react";
import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
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


  //  Datakatkosvalmius
  const [loading, setLoading] = useState(false);
  const [systemStatus, setSystemStatus] = useState("online");
  const [errorMessage, setErrorMessage] = useState(null);

  // manuaalinen säiliötietojen päivitys
  useEffect(() => {
    refreshContainers();
  }, []);

  // kellon päivitys
  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentTime(new Date());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  const formattedDate = currentTime.toLocaleDateString("fi-FI");
  const formattedTime = currentTime.toLocaleTimeString("fi-FI");


  //  Automaattinen tehtävälogiikka hälytyksistä
  useEffect(() => {
    setTasks(prevTasks => {
      let updatedTasks = [...prevTasks];

      containers.forEach(container => {
        if (container.fillLevel >= 70) {

          const exists = updatedTasks.some(
            task => task.containerId === container.id
          );

          if (!exists) {
            updatedTasks.push({
              id: "auto-" + container.id,
              containerId: container.id,
              containerName: container.location,
              alertLevel: container.fillLevel,
              priority: container.fillLevel >= 85 ? "Kriittinen" : "Varoitus",
              assignedTo: "Ei osoitettu",
              createdAt: new Date().toISOString()
            });
          }
        }
      });

      return updatedTasks;
    });
  }, [containers]);

  //  Tehtävän suoritus
  const handleCompleteTask = (taskId, containerId) => {

    setContainers(prev =>
      prev.map(c =>
        c.id === containerId
          ? { ...c, fillLevel: 0 }
          : c
      )
    );

    setTasks(prevTasks => {
      const taskToComplete = prevTasks.find(t => t.id === taskId);

      if (taskToComplete) {
        setCompletedTasks(prevCompleted => {
          const alreadyExists = prevCompleted.some(t => t.id === taskId);
          if (alreadyExists) return prevCompleted;

          return [
            ...prevCompleted,
            {
              ...taskToComplete,
              completedAt: new Date().toISOString()
            }
          ];
        });
      }

      return prevTasks.filter(t => t.id !== taskId);
    });
  };

  //  Päivitys – nappia painamalla, hakee säiliöiden tiedot backendistä
  const refreshContainers = async () => {
    setLoading(true);
    setErrorMessage(null);


    try {
      const response = await fetch("http://localhost:8080/api/sites");
      if (!response.ok) throw new Error("Palvelinvirhe");

      const data = await response.json();

      const mapped = data.map(c => ({
        id: c.id.toString(),
        name: c.name,
        location: c.location,
        fillLevel: c.fillPercent,
        capacity: c.capacity,
        status: c.fillPercent >= 85 ? "critical" :
          c.fillPercent >= 70 ? "warning" : "normal",
        lastUpdate: new Date().toLocaleTimeString(),
        isOnline: true
      }));

      setContainers(mapped);
      setSystemStatus("online");

    } catch (error) {
      setSystemStatus("offline");
      setErrorMessage("Yhteys palvelimeen katkennut");
    } finally {
      setLoading(false);
    }
  };
  useEffect(() => {
    refreshContainers();
    const interval = setInterval(refreshContainers, 5000);
    return () => clearInterval(interval);
  }, []);

  return (
    <Router>
      <div className="app">
        <header className="header">

          {/* YLÄRIVI */}
          <div className="position-relative d-flex align-items-center px-3">

            {/* KESKELLE */}
            <div className="header-title position-absolute top-50 start-50 translate-middle text-center">
              <h1>Älykäs jäteastioiden seuranta</h1>
              <p>Pilotissa 5 kohdetta</p>
            </div>

            {/* OIKEA REUNA */}
            <div className="ms-auto text-end">
              <div>{formattedDate}</div>
              <div>{formattedTime}</div>
            </div>

          </div>

         

          {/* ALARIVI (NAVBAR) */}
          <nav className="navbar navbar-expand-lg bg-body-tertiary mt-3">
            <div className="container-fluid">
              <button
                className="navbar-toggler"
                type="button"
                data-bs-toggle="collapse"
                data-bs-target="#navbarNavAltMarkup"
              >
                <span className="navbar-toggler-icon"></span>
              </button>

              <div className="collapse navbar-collapse" id="navbarNavAltMarkup">

                {/* Keskellä navigointi */}
                <div className="navbar-nav justify-content-center w-100">
                  <NavLink to="/" className={({ isActive }) => "nav-link mx-3 " + (isActive ? "active-link" : "")}>
                    Tilannekuva
                  </NavLink>
                  <NavLink to="/tehtavat" className={({ isActive }) => "nav-link mx-3 " + (isActive ? "active-link" : "")}>
                    Työtehtävät
                  </NavLink>
                  <NavLink to="/sailiot" className={({ isActive }) => "nav-link mx-3 " + (isActive ? "active-link" : "")}>
                    Säiliöt
                  </NavLink>
                  <NavLink to="/raportit" className={({ isActive }) => "nav-link mx-3 " + (isActive ? "active-link" : "")}>
                    Raportit
                  </NavLink>
                </div>

                {/* Oikeaan reunaan asetukset */}
                <div className="d-flex">
                  <div className="dropdown">
                    <button
                      className="btn btn-link nav-link dropdown-toggle"
                      type="button"
                      data-bs-toggle="dropdown"
                    >
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
                onRefresh={refreshContainers} // <-- nappi Dashboardissa kutsuu tätä
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
              <AlertSettings
                alertSettings={alertSettings}
                setAlertSettings={setAlertSettings}
              />
            } />
          </Routes>
        </main>
      </div>
    </Router>
  );
}
