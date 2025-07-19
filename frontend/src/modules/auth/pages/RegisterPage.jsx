import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';
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
      <img src="https://img1.picmix.com/output/stamp/normal/1/6/2/4/2304261_9a959.png" alt="img" className='w-screen'/>
      <form onSubmit={handleSubmit(onSubmit)}>
        <p>Nombre(s)</p>
        <input type="text"
        {...register('name', {required: true})}/>
        <p>Apellido paterno</p>
        <input type='text'
        {...register('paternalSurname', {required: false})}/>
        <p>Apellido materno</p>
        <input type="text"
        {...register('maternalSurname', {required: false})}/>
        <p>Correo electrónico</p>
        <input type="email"
        {...register('email', {required: true})}/>
        <p>Contraseña</p>
        <input type="password"
        {...register('password', {required: true})}/>
        <p>Número de teléfono</p>
        <input type="number"
        {...register('phoneNumber', {required: true, minLength: 10, maxLength: 10})}/>

        <p>Estado en el que reside</p>
        <input type="text"
        {...register('state', {required: true})}/>
        <p>Municipio en el que reside</p>
        <input type="text" placeholder=''
        {...register('municipality', {required: true})}/>
        <p>Código Postal</p>
        <input type="text"
        {...register('zipCode', {required: true})}/>
        <p>Colonia</p>
        <input type="text" 
        {...register('neighborhood', {required: true})}/>

        <p>Calle</p>
        <input type="text"
        {...register('street', {required: true})}/>
        <p>Número exterior</p>
        <input type="text"
        {...register('houseNumber', {required: true})}/>

        <button type='submit'>¡Registrarme!</button>
      </form>
    </div>
  )
}

export default RegisterPage