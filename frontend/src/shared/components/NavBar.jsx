import React from 'react'
import { Link } from 'react-router-dom'
import { request } from '@shared'

function NavBar() {
  return (
    <header className='text-white font-semibold p-3 bg-dark-blue text-center'>
        <ul>
            <Link to="/profile">PERFIL</Link>
            <Link to="/">INICIO</Link>
            <Link to="/cart">CARRITO</Link>
        </ul>
    </header>
  )
}

export default NavBar