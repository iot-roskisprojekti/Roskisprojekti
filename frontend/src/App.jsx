import React, { useState, useEffect } from "react";
import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import Dashboard from "./Dashboard";
import Tasks from "./Tasks";
import Containers from "./Containers";
import Reports from "./Reports";

export default function App() {

  const [containers, setContainers] = useState([]);
  const [tasks, setTasks] = useState([]);
  const [completedTasks, setCompletedTasks] = useState([]);

  //  Datakatkosvalmius
  const [loading, setLoading] = useState(false);
  const [systemStatus, setSystemStatus] = useState("online"); 
  const [errorMessage, setErrorMessage] = useState(null);

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

  //  Päivitys – nappia painamalla
  const refreshContainers = async () => {

    setLoading(true);
    setErrorMessage(null);

    try {

      //  Hae backendistä säiliöt
      const response = await fetch("http://localhost:8080/api/sites");
      if (!response.ok) throw new Error("Palvelinvirhe");

      const data = await response.json();

      //  Simulaatio satunnaisella täyttöasteen muutoksella
      const simulated = data.map(c => {
        const randomChange = Math.floor(Math.random() * 20) - 5;
        let newFill = c.fillPercent + randomChange;
        newFill = Math.max(0, Math.min(100, newFill));

        //  Lähetetään backendille päivitys
        fetch(`http://localhost:8080/api/sites/${c.id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ fillPercent: newFill })
        }).catch(e => console.error("Virhe backend-päivityksessä:", e));

        return {
          id: c.id.toString(),
          location: c.name,
          fillLevel: newFill,
          capacity: 100,
          status: newFill >= 85 ? "critical" :
                  newFill >= 70 ? "warning" : "normal",
          lastUpdate: new Date().toLocaleTimeString(),
          isOnline: true
        };
      });

      setContainers(simulated);
      setSystemStatus("online");

    } catch (error) {
      setSystemStatus("offline");
      setErrorMessage("Yhteys palvelimeen katkennut");
    } finally {
      setLoading(false);
    }
  };

  return (
    <Router>
      <div className="app">

        <header className="header">
          <div className="header-title">
            <h1>Älykäs jäteastioiden seuranta</h1>
            <p>Pilotissa 5 kohdetta</p>
          </div>

          <nav className="navbar navbar-expand-lg bg-body-tertiary">
            <div className="container-fluid">
              <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup">
                <span className="navbar-toggler-icon"></span>
              </button>
              <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div className="navbar-nav justify-content-center w-100">
                  <Link className="nav-link mx-3" to="/">Tilannekuva</Link>
                  <Link className="nav-link mx-3" to="/tehtavat">Työtehtävät</Link>
                  <Link className="nav-link mx-3" to="/sailiot">Säiliöt</Link>
                  <Link className="nav-link mx-3" to="/raportit">Raportit</Link>
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
          </Routes>
        </main>
      </div>
    </Router>
  );
}
