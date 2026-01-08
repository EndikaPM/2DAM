import "./HederComponet.css";

function HederComponet(props){
{/*Como en el comentario anterior recalcamos que e nombre debe coincicir con el 
    que has puesta antes ya que el hijo lo reconece asi solo */}
    const {saludos, links} = props;

    return (
        <header className="header">
            <h1 className="title">{saludos}</h1>
            <nav>
                <ul className="header-list">
                    <li>
                        <a className="link"  href="#">{links.home}</a>
                    </li>
                    <li>
                        <a className="link" href="#">{links.blog}</a>
                    </li>
                    <li>
                        <a className="link" href="#">{links.news}</a>
                    </li>
                    <li>
                        <a className="link" href="#">{links.contact}</a>
                    </li>
                </ul>
            </nav>
        </header>
    )
}

export default HederComponet;