import React from "react";
import { LineChart, Line, XAxis, YAxis, Tooltip, ResponsiveContainer } from "recharts";

const Reports = ({ containers }) => {
  // Esimerkki: trendidata syntyy satunnaisesti
  const sampleData = containers.map((c) => ({
    name: c.location,
    level: c.currentLevel,
  }));

  return (
    <section className="mb-6">
      <h2 className="text-xl font-semibold mb-2">Raportti</h2>
      <ResponsiveContainer width="100%" height={200}>
        <LineChart data={sampleData}>
          <XAxis dataKey="name" />
          <YAxis />
          <Tooltip />
          <Line type="monotone" dataKey="level" stroke="#8884d8" />
        </LineChart>
      </ResponsiveContainer>
    </section>
  );
};

export default Reports;
