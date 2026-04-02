import React, { useMemo } from "react";
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

  // STATUS LOGIIKKA (yhdenmukainen koko appiin)
  const getPriority = (bin) => {
    if (!bin.isOnline) return 4;        // offline aina ylimmäksi
    if (bin.fillLevel >= 85) return 3;  // kriittinen
    if (bin.fillLevel >= 70) return 2;  // varoitus
    return 1;                           // normaali
  };

  const sortedContainers = useMemo(() => {
    return [...containers].sort((a, b) => {
      const diff = getPriority(b) - getPriority(a);

      // jos sama prioriteetti-> eniten täynnä ensin
      if (diff === 0) {
        return (b.fillLevel || 0) - (a.fillLevel || 0);
      }

      return diff;
    });
  }, [containers]);

  return (
    <div style={{ padding: "20px" }}>

      {/* SYSTEM ALERTS */}
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

      {/* HEADER */}
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

        {/* manuaalinen päivitä säiliötiedot -nappi kommentoitu pois
        <button
          className="btn btn-primary"
          onClick={onRefresh}
          style={{ position: "absolute", right: 0 }}
        >
          Päivitä säiliötiedot
        </button>

        */}
      </div>

      {/* GRID */}
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

          const isCritical = !bin.isOnline ? false : bin.fillLevel >= 85;
          const isWarning = !bin.isOnline ? false : bin.fillLevel >= 70 && bin.fillLevel < 85;

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
              isStale={bin.isStale}
              hasTask={hasTask}
              isCritical={isCritical}
              isWarning={isWarning}
              isOffline={!bin.isOnline}
            />
          );
        })}
      </div>
    </div>
  );
}