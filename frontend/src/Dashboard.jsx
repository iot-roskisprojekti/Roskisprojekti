import React from "react";
import BinCard from "./BinCard";

export default function Dashboard({
  containers = [],
  tasks = [],
  onRefresh,
  systemStatus,
  errorMessage
}) {

  const criticalBins = containers.filter(bin => bin.fillLevel >= 85);
  const warningBins = containers.filter(bin => bin.fillLevel >= 70 && bin.fillLevel < 85);
  const normalBins = containers.filter(bin => bin.fillLevel < 70);
  const offlineBins = containers.filter(bin => bin.isOnline === false);
  const totalBins = containers.length;

  const renderBinsColumn = (bins) => (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        gap: "16px",
        alignItems: "center"
      }}
    >
      {bins.map(bin => {
        const hasTask = tasks?.some(
          task => task.containerId === bin.id
        );

        return (
          <BinCard
            key={bin.id}
            id={bin.id}
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
  );

  return (
    <div style={{ padding: "20px" }}>

      
      {/*  DATAYHTEYS-HÄLYTYKSET */}
      
      {systemStatus === "offline" &&  (
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

      
      {/* SÄILIÖSARAKKEET */}
     
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "flex-start",
          gap: "20px"
        }}
      >

        {/* 🔴 Kriittiset */}
        <div>
          <h3 style={{ color: "red", textAlign: "center" }}>
            Kriittiset ({criticalBins.length}/{totalBins})
          </h3>
          {criticalBins.length > 0
            ? renderBinsColumn(criticalBins)
            : <p>Ei kriittisiä säiliöitä</p>}
        </div>

        {/* 🟠 Varoitus */}
        <div>
          <h3 style={{ color: "orange", textAlign: "center" }}>
            Varoitustason säiliöt ({warningBins.length}/{totalBins})
          </h3>
          {warningBins.length > 0
            ? renderBinsColumn(warningBins)
            : <p>Ei varoitustason säiliöitä</p>}
        </div>

        {/* 🟢 Normaali */}
        <div>
          <h3 style={{ color: "green", textAlign: "center" }}>
            Normaalit ({normalBins.length}/{totalBins})
          </h3>
          {normalBins.length > 0
            ? renderBinsColumn(normalBins)
            : <p>Ei normaaleja säiliöitä</p>}
        </div>

      </div>
    </div>
  );
}