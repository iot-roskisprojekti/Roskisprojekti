import React, { useState, useEffect } from "react";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  BarChart,
  Bar,
} from "recharts";

export default function Reports({ containers, tasks }) {
  const [selectedContainer, setSelectedContainer] = useState(containers[0]);
  const [containerHistory, setContainerHistory] = useState([]);

  // Luo päivähistoria 14 päivälle
  const generateDailyHistory = (fillLevel) => {
    const days = 14;
    let history = [];
    let fill = Math.max(0, fillLevel - 50);
    for (let i = days - 1; i >= 0; i--) {
      const date = new Date();
      date.setDate(date.getDate() - i);
      const timestamp = `${date.getDate()}.${date.getMonth() + 1}`;
      fill += Math.floor(Math.random() * 50);
      if (fill > 100) fill = 100;
      history.push({ timestamp, fillLevel: fill });
    }
    return history;
  };

  useEffect(() => {
    setContainerHistory(
      selectedContainer.history || generateDailyHistory(selectedContainer.fillLevel)
    );
  }, [selectedContainer]);

  // Työtehtävien yhteenveto
  const tasksSummary = tasks.reduce((acc, t) => {
    acc[t.assignedTo] = (acc[t.assignedTo] || 0) + 1;
    return acc;
  }, {});
  const barData = Object.entries(tasksSummary).map(([name, count]) => ({
    name,
    count,
  }));

  return (
    <section className="p-6 flex flex-col items-center space-y-8">
      <h2 className="text-2xl font-bold text-center">Raportointi</h2>

      {/* Säiliön valinta */}
      <div className="w-full max-w-md p-4 bg-white rounded shadow text-center">
        <label className="font-medium mr-2">Valitse säiliö:</label>
        <select
          value={selectedContainer.id}
          onChange={(e) =>
            setSelectedContainer(
              containers.find((c) => c.id === e.target.value)
            )
          }
          className="border px-2 py-1 rounded"
        >
          {containers.map((c) => (
            <option key={c.id} value={c.id}>
              {c.location}
            </option>
          ))}
        </select>
      </div>

      {/* Säiliön tiedot */}
      <div className="w-full max-w-md p-4 bg-white rounded shadow text-center">
        <h3 className="font-semibold mb-2">{selectedContainer.location}</h3>
        <p>Kapasiteetti: {selectedContainer.capacity} L</p>
        <p>Täyttöaste: {selectedContainer.fillLevel}%</p>
        <p>Viimeisin päivitys: {selectedContainer.lastUpdate}</p>
        <p>Status: {selectedContainer.isOnline ? "Online" : "Offline"}</p>
      </div>

      {/* Trendikaavio kortilla */}
      <div className="w-full max-w-3xl p-4 bg-white rounded shadow">
        <h3 className="font-semibold mb-4 text-center">
          Täyttöasteen trendi (päiväkohtainen): {selectedContainer.location}
        </h3>
        <LineChart width={600} height={300} data={containerHistory}>
          <XAxis dataKey="timestamp" />
          <YAxis unit="%" domain={[0, 100]} />
          <Tooltip />
          <CartesianGrid stroke="#eee" strokeDasharray="5 5" />
          <Line
            type="monotone"
            dataKey="fillLevel"
            stroke="#646cff"
            dot={{ r: 4, fill: "#646cff" }}
          />
        </LineChart>
      </div>

      {/* Suoritetut työtehtävät */}
      <div className="flex flex-col md:flex-row md:space-x-8 w-full justify-center">
        <div className="flex-1 p-4 bg-white rounded shadow mb-6 md:mb-0">
          <h3 className="font-semibold mb-2 text-center">
            Suoritetut työtehtävät per työntekijä
          </h3>
          <BarChart width={400} height={300} data={barData}>
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <CartesianGrid stroke="#eee" strokeDasharray="5 5" />
            <Bar dataKey="count" fill="#61dafb" />
          </BarChart>
        </div>
      </div>
    </section>
  );
}
