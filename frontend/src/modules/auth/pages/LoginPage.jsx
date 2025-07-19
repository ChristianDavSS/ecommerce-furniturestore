import React from 'react'
import { request } from '@shared'
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form'

function LoginPage() {
    const {register, handleSubmit} = useForm();
    const navigate = useNavigate();

    const onSubmit = async (data) => {
        try {
            await request.post('/auth/login', data)
            navigate("/")
        }catch(e){
            if (e.status == 403) alert("No hay usuarios registrados con estas credenciales. Intentalo de nuevo")
        }
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>
            <input type="email" placeholder='email' name='email'
            {...register('email', {required: true})}/>
            <input type="password" placeholder='password' name='password'
            {...register('password', {required: true})}/>
            <button type='submit'>Login</button>
        </form>
    )
}

export default LoginPage