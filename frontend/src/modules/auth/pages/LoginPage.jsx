import React from 'react'
import { request } from '@shared'
import { useNavigate, Link } from 'react-router-dom';
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
        <div className='grid grid-cols-2 text-xl'>
            <img src="https://static.vecteezy.com/system/resources/previews/011/315/479/non_2x/illustration-of-people-working-together-free-png.png" alt="img" className='w-screen h-screen bg-blue-950'/>
            <div className='flex flex-col items-center justify-center bg-blue-900 text-white'>
                <p className='font-semibold p-5'>Es bueno verte de nuevo 😊</p>
                <form onSubmit={handleSubmit(onSubmit)} className='flex flex-col w-[19rem]'>
                    <p className='font-semibold'>Correo electrónico</p>
                    <input type="email" className='bg-white/10 rounded-sm'
                    {...register('email', {required: true})}/>
                    <p className='font-semibold'>Contraseña</p>
                    <input type="password" className='bg-white/10 rounded-sm'
                    {...register('password', {required: true})}/>
                    <button type='submit' className='bg-white text-black font-semibold p-2 m-5 rounded-xl cursor-pointer'>Iniciar sesión</button>
                    <Link to="/register" className='text-[1rem] text-center'>¿No tienes una cuenta? ¡Registrate ahora!</Link>
                </form>
            </div>
        </div>
    )
}

export default LoginPage