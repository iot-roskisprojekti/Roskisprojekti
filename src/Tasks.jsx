import React from "react";

const Tasks = ({ tasks }) => {
  const handleComplete = async (taskId) => {
    await fetch(`/api/tasks/${taskId}`, {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ status: "completed" }),
    });
  };

  return (
    <section className="mb-6">
      <h2 className="text-xl font-semibold mb-2">Työtehtävät</h2>
      {tasks.length === 0 ? (
        <p>Ei avoimia tehtäviä</p>
      ) : (
        <ul>
          {tasks.map((t) => (
            <li
              key={t.id}
              className="p-3 mb-2 bg-white rounded-2xl shadow-sm flex justify-between items-center"
            >
              <span>
                {t.containerName} - {t.alertLevel}% täyttöaste - {t.assignedTo}
              </span>
              <button
                onClick={() => handleComplete(t.id)}
                className="px-3 py-1 bg-green-500 text-white rounded-xl"
              >
                Kuittaa suoritetuksi
              </button>
            </li>
          ))}
        </ul>
      )}
    </section>
  );
};

export default Tasks;
