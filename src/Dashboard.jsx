import React from "react";

const Dashboard = ({ containers }) => {
  return (
    <section className="mb-6">
      <h2 className="text-xl font-semibold mb-2">Tilannekuva</h2>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {containers.map((c) => (
          <div
            key={c.id}
            className={`p-4 rounded-2xl shadow-md ${
              c.currentLevel >= 90 ? "bg-red-100" : "bg-green-100"
            }`}
          >
            <h3 className="font-bold mb-2">{c.location}</h3>
            <div className="w-full h-4 bg-gray-200 rounded-full mb-2">
              <div
                className="h-4 rounded-full bg-blue-500"
                style={{ width: `${(c.currentLevel / c.maxCapacity) * 100}%` }}
              ></div>
            </div>
            <p>{c.currentLevel}% täynnä</p>
          </div>
        ))}
      </div>
    </section>
  );
};

export default Dashboard;
