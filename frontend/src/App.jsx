import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import { LoginPage, RegisterPage, MainPage, ProductPage } from '@modules'
import { Layout } from '@shared'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/login' element={<LoginPage />} />
        <Route path='/register' element={<RegisterPage />} />

        <Route element = {<Layout />}>
          <Route path = "/" element={<MainPage />} />
          <Route path = "/product/:id" element = {<ProductPage />}/>
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
