import React, { useEffect } from 'react'
import { useNavigate, Link } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { request } from '@shared'

function RegisterPage() {
  const { register, handleSubmit } = useForm();
  const navigate = useNavigate();

  const onSubmit = async (form) => {
    try {
      await request.post("/user/register", form)
      navigate("/")
    }catch(e){
      if (e.status == 406){
        alert("Este correo ya fue registrado.")
        navigate("/login")
      }
    }
  }

  return (
    <div className='grid grid-cols-2'>
      <img src="https://static.vecteezy.com/system/resources/previews/011/315/479/non_2x/illustration-of-people-working-together-free-png.png" alt="img" className='w-screen h-screen bg-blue-950'/>
      <div className='flex flex-col items-center justify-center bg-blue-900'>
        <p className='text-xl font-semibold text-white p-4'>¡Bienvenido!</p>
        <form onSubmit={handleSubmit(onSubmit)} className='flex flex-col w-[19rem] text-white'>
          <p className='font-semibold'>Nombre(s)</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('name', {required: true})}/>
          
          <p className='font-semibold'>Apellido paterno</p>
          <input type='text' className='bg-white/10 rounded-sm'
          {...register('paternalSurname', {required: false})}/>

          <p className='font-semibold'>Apellido materno</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('maternalSurname', {required: false})}/>

          <p className='font-semibold'>Correo electrónico</p>
          <input type="email" className='bg-white/10 rounded-sm'
          {...register('email', {required: true})}/>

          <p className='font-semibold'>Contraseña</p>
          <input type="password" className='bg-white/10 rounded-sm'
          {...register('password', {required: true})}/>

          <p className='font-semibold'>Número de teléfono</p>
          <input type="number" className='bg-white/10 rounded-sm'
          {...register('phoneNumber', {required: true, minLength: 10, maxLength: 10})}/>

          <p className='font-semibold'>Estado en el que reside</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('state', {required: true})}/>

          <p className='font-semibold'>Municipio en el que reside</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('municipality', {required: true})}/>

          <p className='font-semibold'>Código Postal</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('zipCode', {required: true})}/>

          <p className='font-semibold'>Colonia</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('neighborhood', {required: true})}/>

          <p className='font-semibold'>Calle</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('street', {required: true})}/>
          
          <p className='font-semibold'>Número exterior</p>
          <input type="text" className='bg-white/10 rounded-sm'
          {...register('houseNumber', {required: true})}/>

          <button type='submit' className='bg-white font-semibold text-black p-2 rounded-xl m-4 cursor-pointer'>¡Registrarme!</button>
          <Link to="/login" className='text-[1rem] text-center'>¿Ya tienes una cuenta? ¡Ingresa ahora!</Link>
        </form>
      </div>
    </div>
  )
}

export default RegisterPage