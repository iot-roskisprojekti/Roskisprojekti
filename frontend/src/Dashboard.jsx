import React from "react";
import BinCard from "./BinCard";

export default function Dashboard({
  containers = [],
  tasks = [],
  onRefresh,
  systemStatus,
  errorMessage
}) {

  const totalBins = containers.length;
  const offlineBins = containers.filter(bin => bin.isOnline === false);

  const sortedContainers = [...containers].sort((a, b) => {
  const priority = (bin) => {
    if (bin.fillLevel >= 85) return 3; // kriittinen
    if (bin.fillLevel >= 70) return 2; // varoitus
    return 1; // normaali
  };

  return priority(b) - priority(a);
});


  return (
    <div style={{ padding: "20px" }}>

      {/* DATAYHTEYS-HÄLYTYKSET */}
      {systemStatus === "offline" && (
        <div className="alert alert-danger text-center">
          <strong>JÄRJESTELMÄ OFFLINE</strong><br />
          Yhteys backend-palvelimeen katkennut.
        </div>
      )}

      {systemStatus !== "offline" && errorMessage && (
        <div className="alert alert-warning text-center">
          <strong>Yhteysvirhe:</strong> {errorMessage}
        </div>
      )}

      {systemStatus !== "offline" && offlineBins.length > 0 && (
        <div className="alert alert-secondary text-center">
          <strong>Säiliöyhteys katkennut:</strong>{" "}
          {offlineBins.length} / {totalBins} säiliötä ei vastaa.
        </div>
      )}

      {/* OTSIKKO + PÄIVITYS */}
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          position: "relative",
          marginBottom: "20px"
        }}
      >
        <h2 className="m-0">Tilannekuva</h2>

        <button
          className="btn btn-primary"
          onClick={onRefresh}
          style={{ position: "absolute", right: 0 }}
        >
          Päivitä säiliötiedot
        </button>
      </div>

      {/* GRID (KAIKKI KORTIT) */}
      <div
        style={{
          display: "grid",
          gridTemplateColumns: "repeat(auto-fit, minmax(260px, 1fr))",
          gap: "16px",
          alignItems: "start"
        }}
      >
        {sortedContainers.map(bin => {
          const hasTask = tasks?.some(
            task => task.containerId === bin.id
          );

          return (
            <BinCard
              key={bin.id}
              id={bin.id}
              name={bin.name}
              location={bin.location}
              fillLevel={bin.fillLevel}
              capacity={bin.capacity}
              lastUpdate={bin.lastUpdate}
              isOnline={bin.isOnline}
              hasTask={hasTask}
            />
          );
        })}
      </div>
    </div>
  );
}
