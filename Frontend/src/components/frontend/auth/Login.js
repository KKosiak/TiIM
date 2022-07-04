import React, { useState } from "react";
import NavbarS from '../../../layout/frontend/NavbarS';
import { useHistory } from "react-router-dom";
import axios from "axios";
import swal from 'sweetalert';

function Login(){

    const history = useHistory();
    const [loginInput, setLogin] = useState({
        email: '',
        password: '',
        error_list: []
    });

    const handleInput = (e) => {
        e.persist();
        setLogin({...loginInput, [e.target.name]: e.target.value});
    }


    const loginSubmit = (e) => {
        e.preventDefault();

        const data = {
            email: loginInput.email,
            password: loginInput.password
        }

        axios.get('http://localhost:8000/sanctum/csrf-cookie').then(response => {
            axios.post('http://localhost:8000/api/login', data).then(res => {
                if(res.data.status === 200){
                    localStorage.setItem('auth_token', res.data.token);
                    localStorage.setItem('auth_name', res.data.username);
                    swal("Success", res.data.message, "success");
                    history.push('/admin');
                }
                else if(res.data.status === 401){
                    swal("Warning", res.data.message, "Warning");
                }
                else{
                    setLogin({...loginInput, error_list: res.data.validation_errors });
                }
            });
        });

    };

    return(
        <div>
            <NavbarS />
            <div className="container py-5">
                <div className="row justify-content-center">
                    <div className="col-md 6">
                        <div className="card">
                            <div className="card-header">
                                <h4>Logowanie</h4>
                            </div>
                            <div className="card-body">
                                <form onSubmit={loginSubmit}>
                                    <div className="form-group mb-3">
                                        <label>Email</label>
                                        <input type="email" name="email" onChange={handleInput} value={loginInput.email} className="form-control"  />
                                        <span>{loginInput.error_list.email}</span>
                                    </div>
                                    <div className="form-group mb-3">
                                        <label>Hasło</label>
                                        <input type="password" name="password"onChange={handleInput} value={loginInput.password} className="form-control" />
                                        <span>{loginInput.error_list.password}</span>
                                    </div>
                                    <div className="form-group mb-3">
                                        <button type="submit" className="btn btn-primary">Zatwierdź</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;