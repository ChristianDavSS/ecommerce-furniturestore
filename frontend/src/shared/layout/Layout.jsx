import React from 'react'
import { Outlet } from 'react-router-dom'
import { NavBar, Footer } from '@shared'

function Layout() {
  return (
    <div className='flex flex-col min-h-screen'>
        <NavBar />
        <main className='grow'><Outlet /></main>
        <Footer />
    </div>
  )
}

export default Layout