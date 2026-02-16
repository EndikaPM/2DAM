import React, { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import TutorialDataService from "../services/tutorial.service";

function TutorialEdit() {
    const { id } = useParams();
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [published, setPublished] = useState(false);

    useEffect(() => {
        if (!id) {
            return;
        }
        TutorialDataService.get(id)
            .then((response) => {
                setTitle(response.data.title || "");
                setDescription(response.data.description || "");
                setPublished(Boolean(response.data.published));
            })
            .catch((e) => {
                console.log(e);
            });
    }, [id]);

    const history = useHistory();

    // Actualiza el título al escribir
    const onChangeTitle = (e) => {
        setTitle(e.target.value);
    };

    // Actualiza la descripción al escribir
    const onChangeDescription = (e) => {
        setDescription(e.target.value);
    };

    // Actualiza el texto de publicado
    const onChangePublished = (e) => {
        setPublished(e.target.checked);
    };

    // Envía el tutorial al backend
    const saveTutorial = () => {
        const data = {
            id: id,
            title,
            description,
            published
        };
        TutorialDataService.update(id, data)
            .then(() => {
                setTitle(data.title);
                setDescription(data.description);
                setPublished(data.published);
                history.goBack();
            })
            .catch((e) => {
                console.log(e);
            });
    };

    return (
        <div className="submit-form">
                <div>
                    <div className="form-group">
                        <label htmlFor="title">Title</label>
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
                        <label htmlFor="description">Description</label>
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

                    <div className="form-group">
                        <label htmlFor="published">Published</label>
                        <div className="form-check">
                            <input
                                type="checkbox"
                                className="form-check-input"
                                id="published"
                                checked={published}
                                onChange={onChangePublished}
                            />
                            <label className="form-check-label" htmlFor="published">
                                Publish
                            </label>
                        </div>
                    </div>
                    
                    <button onClick={saveTutorial} className="btn btn-success mt-3">    
                        Actualiar Tutorial
                    </button>
                </div>
        </div>
    );
}
export default TutorialEdit;