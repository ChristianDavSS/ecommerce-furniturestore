import React from 'react'

function Footer() {
  return (
    <footer className='text-gray-300 text-center bg-dark-blue p-4 font-semibold'>
      <h2 className='text-xl p-2'>Mueblería</h2>
      <p className='pb-4'>Todos los derechos reservados © 2025</p>
      <div className='grid grid-cols-3'>
        <div className='flex flex-col space-y-2'>
          <a href="">Terminos y condiciones.</a>
          <a href="">Aviso legal.</a>
          <a href="">Preguntas frecuentes.</a>
        </div>
        <div className='border-l-2 space-y-2'>
          <p>Oficinas</p>
          <p>(+52) 462 246 5400</p>
          <p>ventas@muebles.com.mx</p>
        </div>
        <div className='space-y-2 border-l-2'>
          <p>Medellín 268</p>
          <p>Centro 28000</p>
          <p>Colima, Col.</p>
        </div>
      </div>
    </footer>
  )
}

export default Footer