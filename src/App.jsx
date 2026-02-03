// App.jsx
import React, { useEffect, useState } from "react";
import Dashboard from "./Dashboard";
import Alerts from "./Alerts";
import Tasks from "./Tasks";
import Reports from "./Reports";

function App() {
  const [containers, setContainers] = useState([]);
  const [alerts, setAlerts] = useState([]);
  const [tasks, setTasks] = useState([]);

  // Haetaan data backendistä
  useEffect(() => {
    fetchContainers();
    fetchAlerts();
    fetchTasks();

    const interval = setInterval(() => {
      fetchContainers();
      fetchAlerts();
      fetchTasks();
    }, 60000); // Päivitetään minuutin välein

    return () => clearInterval(interval);
  }, []);

  const fetchContainers = async () => {
    const res = await fetch("/api/containers");
    const data = await res.json();
    setContainers(data);
  };

  const fetchAlerts = async () => {
    const res = await fetch("/api/alerts");
    const data = await res.json();
    setAlerts(data);
  };

  const fetchTasks = async () => {
    const res = await fetch("/api/tasks");
    const data = await res.json();
    setTasks(data);
  };

  return (
    <div className="p-4 bg-gray-50 min-h-screen">
      <header className="mb-6">
        <h1 className="text-2xl font-bold">Älykäs jäteastioiden seuranta</h1>
        <p className="text-gray-600">Pilotissa 5 kohdetta</p>
      </header>
      <Dashboard containers={containers} />
      <Alerts alerts={alerts} />
      <Tasks tasks={tasks} />
      <Reports containers={containers} />
    </div>
  );
}

export default App;
