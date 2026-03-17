import React, { useState } from "react";

export default function NotificationSettings() {
  const [contacts, setContacts] = useState([]);
  const [form, setForm] = useState({
    id: null,
    name: "",
    phone: "",
    email: ""
  });
  const [isEditing, setIsEditing] = useState(false);

  // input handler
  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  // lisää tai päivitä
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!form.name || !form.phone || !form.email) return;

    if (isEditing) {
      setContacts(prev =>
        prev.map(c => (c.id === form.id ? form : c))
      );
    } else {
      setContacts(prev => [
        ...prev,
        { ...form, id: Date.now().toString() }
      ]);
    }

    resetForm();
  };

  const resetForm = () => {
    setForm({ id: null, name: "", phone: "", email: "" });
    setIsEditing(false);
  };

  // poisto
  const handleDelete = (id) => {
    setContacts(prev => prev.filter(c => c.id !== id));
  };

  // muokkaus
  const handleEdit = (contact) => {
    setForm(contact);
    setIsEditing(true);
  };

  return (
    <div className="container mt-4">
      <h2>Ilmoitusten vastaanottajat</h2>

      {/* FORM */}
      <form onSubmit={handleSubmit} className="mb-4">

        <div className="mb-2">
          <input
            className="form-control"
            type="text"
            name="name"
            placeholder="Nimi"
            value={form.name}
            onChange={handleChange}
          />
        </div>

        <div className="mb-2">
          <input
            className="form-control"
            type="text"
            name="phone"
            placeholder="Puhelinnumero"
            value={form.phone}
            onChange={handleChange}
          />
        </div>

        <div className="mb-2">
          <input
            className="form-control"
            type="email"
            name="email"
            placeholder="Sähköposti"
            value={form.email}
            onChange={handleChange}
          />
        </div>

        <button className="btn btn-primary me-2" type="submit">
          {isEditing ? "Tallenna muutokset" : "Lisää yhteystieto"}
        </button>

        {isEditing && (
          <button
            type="button"
            className="btn btn-secondary"
            onClick={resetForm}
          >
            Peruuta
          </button>
        )}
      </form>

      {/* LISTA */}
      <table className="table table-striped">
        <thead>
          <tr>
            <th>Nimi</th>
            <th>Puhelin</th>
            <th>Sähköposti</th>
            <th>Toiminnot</th>
          </tr>
        </thead>
        <tbody>
          {contacts.length === 0 ? (
            <tr>
              <td colSpan="4">Ei yhteystietoja</td>
            </tr>
          ) : (
            contacts.map(contact => (
              <tr key={contact.id}>
                <td>{contact.name}</td>
                <td>{contact.phone}</td>
                <td>{contact.email}</td>
                <td>
                  <button
                    className="btn btn-sm btn-warning me-2"
                    onClick={() => handleEdit(contact)}
                  >
                    Muokkaa
                  </button>
                  <button
                    className="btn btn-sm btn-danger"
                    onClick={() => handleDelete(contact.id)}
                  >
                    Poista
                  </button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </table>
    </div>
  );
}