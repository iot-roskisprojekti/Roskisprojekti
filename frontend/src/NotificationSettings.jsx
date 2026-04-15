import React, { useState, useEffect } from "react";

export default function NotificationSettings() {
  const [contacts, setContacts] = useState([]);
  const [form, setForm] = useState({
    id: null,
    name: "",
    phone: "",
    email: ""
  });
  const [isEditing, setIsEditing] = useState(false);

  //api kutsut backendille

  const API_URL = "http://localhost:8080/api/contacts";

  // HAE kaikki yhteystiedot backendista
  useEffect(() => {
    fetch(API_URL)
      .then(res => res.json())
      .then(data => setContacts(data))
      .catch(err => console.error("Virhe haussa:", err));
  }, []);



  // input handler
  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };



  //kutsut backendille

  // LISÄÄ tai PÄIVITÄ (POST / PUT)
  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!form.name || !form.phone || !form.email) return;

    try {
      if (isEditing) {
        // UPDATE
        const res = await fetch(`${API_URL}/${form.id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(form)
        });

        const updated = await res.json();

        setContacts(prev =>
          prev.map(c => (c.id === form.id ? updated : c))
        );

      } else {
        // CREATE
        const res = await fetch(API_URL, {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(form)
        });

        const newContact = await res.json();

        setContacts(prev => [...prev, newContact]);
      }

      resetForm();

    } catch (err) {
      console.error("Virhe tallennuksessa:", err);
    }
  };

  const resetForm = () => {
    setForm({ id: null, name: "", phone: "", email: "" });
    setIsEditing(false);
  };

  // DELETE
  const handleDelete = async (id) => {
    const confirmed = window.confirm("Haluatko varmasti poistaa yhteystiedon?");
    if (!confirmed) return;
    
    try {
      await fetch(`${API_URL}/${id}`, {
        method: "DELETE"
      });

      setContacts(prev => prev.filter(c => c.id !== id));

    } catch (err) {
      console.error("Virhe poistossa:", err);
    }
  };

  // EDIT
  const handleEdit = (contact) => {
    setForm(contact);
    setIsEditing(true);
  };



  return (
    <div className="container mt-4">
      <h2>Ilmoitusten vastaanottajat</h2>

      {/*form*/}
      <form onSubmit={handleSubmit} className="mb-4">

        <input
          className="form-control mb-2"
          type="text"
          name="name"
          placeholder="Nimi"
          value={form.name}
          onChange={handleChange}
        />

        <input
          className="form-control mb-2"
          type="text"
          name="phone"
          placeholder="Puhelinnumero"
          value={form.phone}
          onChange={handleChange}
        />

        <input
          className="form-control mb-2"
          type="email"
          name="email"
          placeholder="Sähköposti"
          value={form.email}
          onChange={handleChange}
        />

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

      {/*lista*/}
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
                    className="btn btn-sm btn-primary me-2"
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