import React from "react";

export default function BinCard({
  id,
  name,
  location,
  fillLevel,
  capacity,
  lastUpdate,
  isOnline,
  isStale,
  hasTask
}) {

  // Status logiikka (täyttöaste)
  const getStatus = (level) => {
    if (level >= 85) return { text: "Kriittinen", color: "#dc3545" };
    if (level >= 70) return { text: "Varoitus", color: "#fd7e14" };
    return { text: "Normaali", color: "#198754" };
  };

  const status = getStatus(fillLevel);

  // Fill bar väri
  let fillColor = status.color;

  // Offline override
  if (!isOnline) {
    fillColor = "#6c757d";
  }

  const cardBg = !isOnline
    ? "#f5f5f5"
    : isStale
      ? "#fff8e1" // vaalea keltainen = vanhentunut data
      : "#fff";

  const textColor = !isOnline ? "#666" : "#000";

  return (
    <div
      style={{
        backgroundColor: cardBg,
        color: textColor,
        padding: "1rem",
        borderRadius: "1rem",
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
        width: "260px",
        textAlign: "center",
        margin: "0.5rem",
        border: hasTask
          ? "3px solid #0d6efd"
          : isStale
            ? "3px solid #ffc107"
            : "3px solid transparent",
        transition: "all 0.2s ease"
      }}
    >
      {/* Nimi */}
      <h3
        style={{
          marginBottom: "0.3rem",
          display: "-webkit-box",
          WebkitLineClamp: 2,
          WebkitBoxOrient: "vertical",
          overflow: "hidden",
          minHeight: "3em" // varaa tila kahdelle riville
        }}
      >
        {name || `Roska-astia ${id}`}
      </h3>

      {/* Status badge */}
      <div
        style={{
          display: "inline-block",
          padding: "4px 10px",
          borderRadius: "12px",
          backgroundColor: !isOnline
            ? "#6c757d"
            : isStale
              ? "#ffc107"
              : status.color,
          color: "white",
          fontSize: "12px",
          fontWeight: "bold",
          marginBottom: "10px"
        }}
      >
        {!isOnline
          ? "Offline"
          : isStale
            ? "Vanhentunut"
            : status.text}
      </div>

      {/* Sijainti */}
      <p style={{ marginBottom: "0.5rem" }}>
        📍 {location}
      </p>

      {/* Fill bar */}
      <div
        style={{
          height: "16px",
          backgroundColor: "#eee",
          borderRadius: "8px",
          overflow: "hidden",
          margin: "0.5rem 0"
        }}
      >
        <div
          style={{
            width: `${fillLevel}%`,
            backgroundColor: fillColor,
            height: "100%",
            transition: "width 0.5s"
          }}
        />
      </div>

      <p>{fillLevel}% täynnä</p>

      {/* Lisätiedot */}
      <p>Kapasiteetti: {capacity} L</p>

      <p>
        Viimeksi päivitetty: {lastUpdate || "Ei tietoa"}
      </p>

      {/* Status info */}
      <p>Status: {isOnline ? "Online" : "Offline"}</p>

      {/* Stale warning */}
      {isStale && isOnline && (
        <div
          style={{
            marginTop: "8px",
            color: "#b8860b",
            fontWeight: "bold",
            fontSize: "12px"
          }}
        >
          ⚠ Mittaus vanhentunut
        </div>
      )}

      {/* Task indicator */}
      {hasTask && (
        <div
          style={{
            marginTop: "10px",
            color: "#0d6efd",
            fontWeight: "bold"
          }}
        >
          🚛 Tyhjennys tilattu
        </div>
      )}
    </div>
  );
}