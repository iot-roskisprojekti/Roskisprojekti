import { useState } from "react";

export default function Containers({ containers, setContainers, setIsEditing }) {
  const [editingId, setEditingId] = useState(null);
  const [editedData, setEditedData] = useState({});
  const [newContainer, setNewContainer] = useState({
    name: "",
    location: "",
    fillLevel: 0,
    capacity: "",
    status: "normal",
    isOnline: true,
    lastUpdate: new Date().toLocaleTimeString(),
  });

  const startEditing = (container) => {
    setIsEditing(true);
    setEditingId(container.id);
    setEditedData({ ...container });
  };

  const cancelEditing = () => {
    setEditingId(null);
    setIsEditing(false);
  };

  const handleChange = (e) => {
    const { name, value, type } = e.target;
    setEditedData({
      ...editedData,
      [name]: type === "number" ? Number(value) : value,
    });
  };

  const saveChanges = async () => {
    console.log("editedData:", editedData);
    console.log("editedData.binId:", editedData.binId);
    console.log("editingId:", editingId);
  try {
    // PUT site (nimi ja sijainti)
    const siteResponse = await fetch(`http://localhost:8080/api/sites/${editingId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        id: Number(editingId),
        name: editedData.name,
        location: editedData.location,
      }),
    });
    if (!siteResponse.ok) throw new Error("Site muokkaus epäonnistui");

    // PUT bin (kapasiteetti) - vain jos binId löytyy
    if (editedData.binId) {
      console.log("Lähetetään bin PUT:", `http://localhost:8080/api/bins/${editedData.binId}`);
      const binResponse = await fetch(`http://localhost:8080/api/bins/${editedData.binId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          binId: editedData.binId,
          siteId: Number(editingId),
          name: editedData.name,
          capacityLiters: editedData.capacity,
          fillLevel: editedData.fillLevel,
        }),
      });
      console.log("Bin response status:", binResponse.status);
      if (!binResponse.ok) throw new Error("Bin muokkaus epäonnistui");
    }

    setContainers(containers.map(c =>
      c.id === editingId
        ? { ...c, name: editedData.name, location: editedData.location, capacity: editedData.capacity }
        : c
    ));
  } catch (error) {
    console.error("Muokkaus epäonnistui:", error);
    alert("Muokkaus epäonnistui. Tarkista backend.");
  } finally {
    setEditingId(null);
    setIsEditing(false);
  }
};

  const deleteContainer = async (id) => {
  if (!window.confirm("Haluatko varmasti poistaa säiliön?")) return;
  try {
    // 1. Etsi binId tästä containerista
    const container = containers.find(c => c.id === id);
    
    // 2. Poista bin ensin jos löytyy
    if (container?.binId) {
      const binResponse = await fetch(`http://localhost:8080/api/bins/${container.binId}`, {
        method: "DELETE",
      });
      if (!binResponse.ok) throw new Error("Bin poisto epäonnistui");
    }

    // 3. Poista site
    const siteResponse = await fetch(`http://localhost:8080/api/sites/${id}`, {
      method: "DELETE",
    });
    if (!siteResponse.ok) throw new Error("Site poisto epäonnistui");

    setContainers(containers.filter(c => c.id !== id));
  } catch (error) {
    console.error("Poisto epäonnistui:", error);
    alert("Poisto epäonnistui. Tarkista backend.");
  }
};

  const handleNewChange = (e) => {
    const { name, value, type } = e.target;
    setNewContainer({
      ...newContainer,
      [name]: type === "number" ? Number(value) : value,
    });
  };

  const addContainer = async () => {
    console.log("addContainer käynnistyy");
    console.log("newContainer:", newContainer);
  if (!newContainer.location) {
    alert("Sijainti on pakollinen");
    return;
  }
  try {
    // 1. Luo site
    const siteResponse = await fetch("http://localhost:8080/api/sites", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        name: newContainer.name,
        location: newContainer.location,
      }),
    });
    if (!siteResponse.ok) throw new Error("Site lisäys epäonnistui");
    const savedSite = await siteResponse.json();
    console.log("Site luotu, id:", savedSite.id);

    // 2. Luo bin sitelle
    const binResponse = await fetch("http://localhost:8080/api/bins", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        siteId: savedSite.id,
        name: newContainer.name,
        capacityLiters: newContainer.capacity,
        fillLevel: 0,
      }),
    });
    if (!binResponse.ok) throw new Error("Bin lisäys epäonnistui");
    const savedBin = await binResponse.json();
    console.log("Bin luotu, capacityLiters:", savedBin.capacityLiters);

    setContainers([...containers, {
      ...newContainer,
      id: String(savedSite.id),
      binId: savedBin.binId,
      capacity: savedBin.capacityLiters,
      isOnline: true,
    }]);

    setNewContainer({
      name: "",
      location: "",
      fillLevel: 0,
      capacity: "",
      status: "normal",
      isOnline: true,
      lastUpdate: new Date().toLocaleTimeString(),
    });
  } catch (error) {
    console.error("Lisäys epäonnistui:", error);
    alert("Lisäys epäonnistui. Tarkista backend.");
  }
};

  const translateStatus = (status) => {
    switch (status) {
      case "normal": return "Normaali";
      case "warning": return "Varoitus";
      case "critical": return "Kriittinen";
      default: return status;
    }
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Säiliöiden hallinta</h2>

      <div className="card mb-4 p-3" style={{ maxWidth: "400px" }}>
        <h5>Lisää uusi säiliö</h5>
        <div className="mb-2">
          <label>Nimi</label>
          <input name="name" placeholder="Nimi" value={newContainer.name} onChange={handleNewChange} className="form-control mb-2" />
          <label>Sijainti</label>
          <input name="location" placeholder="Sijainti" value={newContainer.location} onChange={handleNewChange} className="form-control mb-2" />
          <label>Kapasiteetti (litroina)</label>
          <input name="capacity" type="number" placeholder="Kapasiteetti" value={newContainer.capacity} onChange={handleNewChange} className="form-control mb-2" />
          <button className="btn btn-success btn-sm" onClick={addContainer}>Lisää säiliö</button>
        </div>
      </div>

      <table className="table table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nimi</th>
            <th>Sijainti</th>
            <th>Täyttöaste</th>
            <th>Kapasiteetti</th>
            <th>Status</th>
            <th>Online</th>
            <th>Toiminnot</th>
          </tr>
        </thead>
        <tbody>
          {containers.map((c) => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>
                {editingId === c.id
                  ? <input name="name" value={editedData.name} onChange={handleChange} className="form-control" />
                  : c.name}
              </td>
              <td>
                {editingId === c.id
                  ? <input name="location" value={editedData.location} onChange={handleChange} className="form-control" />
                  : c.location}
              </td>
              <td>{c.fillLevel}%</td>
              <td>
                {editingId === c.id
                  ? <input name="capacity" type="number" value={editedData.capacity} onChange={handleChange} className="form-control" />
                  : c.capacity}
              </td>
              <td>{translateStatus(c.status)}</td>
              <td>{c.isOnline ? "Kyllä" : "Ei"}</td>
              <td>
                {editingId === c.id ? (
                  <>
                    <button className="btn btn-success btn-sm me-1" onClick={saveChanges}>Tallenna</button>
                    <button className="btn btn-secondary btn-sm" onClick={cancelEditing}>Peruuta</button>
                  </>
                ) : (
                  <>
                    <button className="btn btn-primary btn-sm me-1" onClick={() => startEditing(c)}>Muokkaa</button>
                    <button className="btn btn-danger btn-sm" onClick={() => deleteContainer(c.id)}>Poista</button>
                  </>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}