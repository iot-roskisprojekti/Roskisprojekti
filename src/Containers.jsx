import React from "react";

export default function Containers({ containers }) {
  return (
    <div style={{ padding: "20px" }}>
      <h2>Säiliöiden hallinta</h2>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Sijainti</th>
            <th>Täyttöaste</th>
            <th>Kapasiteetti</th>
            <th>Status</th>
            <th>Online</th>
          </tr>
        </thead>
        <tbody>
          {containers.map((c) => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>{c.location}</td>
              <td>{c.fillLevel}%</td>
              <td>{c.capacity} L</td>
              <td>{c.status}</td>
              <td>{c.isOnline ? "Kyllä" : "Ei"}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
