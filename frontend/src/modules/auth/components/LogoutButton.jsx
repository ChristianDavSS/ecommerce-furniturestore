import React from 'react'
import { request } from '@shared'
import { useNavigate } from 'react-router-dom'

function Logout() {
    const navigate = useNavigate();

    const logout = async () =>{
        await request.post("/auth/logout");
        navigate("/");
    }

    return (
        <button onClick={logout}>CERRAR SESIÓN</button>
    )
}

export default Logout