import React from "react";
import {Link} from 'react-router-dom';

function NavbarS(){
    return(
        <nav className="navbar navbar-expand-lg navbar-dark bg-primary shadow sticky-top">
            <div className="container">
                <Link className="navbar-brand" to="#">Sklep</Link>
                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                        <Link className="nav-link active" to="/">Strona główna</Link>
                        </li>
                        <li className="nav-item">
                        <Link className="nav-link" to="/login">Logowanie</Link>
                        </li>
                        <li className="nav-item">
                        <Link className="nav-link" to="/register">Rejestracja</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default NavbarS;