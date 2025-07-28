import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import { LoginPage, RegisterPage, MainPage, ProductPage, ProfilePage } from '@modules'
import { Layout, request, AuthWrapper } from '@shared'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<AuthWrapper />}>
          <Route path='/login' element={<LoginPage />} />
          <Route path='/register' element={<RegisterPage />} />
        </Route>

        <Route element = {<Layout />}>
          <Route path = "/" element={<MainPage />} />
          <Route path = "/product/:id" element = {<ProductPage />}/>
          <Route path="/profile" element={<ProfilePage />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
