import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import TutorialDataService from "../services/tutorial.service";


function AddTutorial() {
    // Estado local con useState
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [published, setPublished] = useState(false);
    const [submitted, setSubmitted] = useState(false);

    // Actualiza el título al escribir
    const onChangeTitle = (e) => {
        setTitle(e.target.value);
    };

    // Actualiza la descripción al escribir
    const onChangeDescription = (e) => {
        setDescription(e.target.value);
    };

    // Actualiza el checkbox de publicado
    const onChangePublished = (e) => {
        setPublished(e.target.checked);
    };
    const history = useHistory();

    // Envía el tutorial al backend
    const saveTutorial = () => {
        const data = {
            title,
            description,
            published
        };

        TutorialDataService.create(data)
            .then((response) => {
                // Guarda la respuesta y marca como enviado
                setTitle(response.data.title);
                setDescription(response.data.description);
                setPublished(response.data.published);
                setSubmitted(true);
                history.goBack();
            })
            .catch((e) => {
                console.log(e);
            });
    };

    // Resetea el formulario
    const newTutorial = () => {
        setTitle("");
        setDescription("");
        setPublished(false);
        setSubmitted(false);
    };

    return (
        <div className="submit-form">
            <div>
                <div className="form-group">
                    <label htmlFor="title">Titulo</label>
                    <input
                        type="text"
                        className="form-control"
                        id="title"
                        required
                        value={title}
                        onChange={onChangeTitle}
                        name="title"
                    />
                </div>

                <div className="form-group">
                    <label htmlFor="description">Descripción</label>
                    <input
                        type="text"
                        className="form-control"
                        id="description"
                        required
                        value={description}
                        onChange={onChangeDescription}
                        name="description"
                    />
                </div>

                <div className="form-group form-check mt-3">
                    <input
                        type="checkbox"
                        className="form-check-input"
                        id="published"
                        checked={published}
                        onChange={onChangePublished}
                    />
                    <label className="form-check-label" htmlFor="published">
                        Publicar
                    </label>
                </div>

                <button onClick={saveTutorial} className="btn btn-success mt-3">
                    Añadir Tutorial
                </button>
            </div>
        </div>
    );
}

export default AddTutorial;