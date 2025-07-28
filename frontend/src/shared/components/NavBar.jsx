import React from 'react'
import { Link } from 'react-router-dom'
import { LogoutButton } from '@modules'

function NavBar() {
  return (
    <header className='text-white font-semibold p-3 bg-dark-blue text-center'>
        <ul>
            <Link to="/profile">PERFIL</Link>
            <Link to="/">INICIO</Link>
            <Link to="/cart">CARRITO</Link>
            <Link to="/login">INICIAR SESIÓN</Link>
            <Link to="/register">REGISTRATE</Link>
            <LogoutButton />
        </ul>
    </header>
  )
}

export default NavBar