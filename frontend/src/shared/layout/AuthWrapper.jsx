import React, { useEffect } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'
import { request } from '@shared'

function AuthWrapper() {
    /*
    Wrapper to avoid authenticated users to go to /login or /register
    */
    const navigate = useNavigate();
    useEffect(()=>{
        const isAuth = async () =>{
            const { data } = await request.get("/auth/isAuth");

            if (data) navigate("/")
        }
        isAuth();
    })

    return <Outlet />
}

export default AuthWrapper