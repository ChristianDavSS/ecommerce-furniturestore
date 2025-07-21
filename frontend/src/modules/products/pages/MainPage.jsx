import React, { useEffect, useState } from 'react'
import { ProductCard } from '@modules';
import { Link } from 'react-router-dom';
import { request } from '@shared'

function MainPage() {
  const [products, setProducts] = useState([]);

  useEffect(()=>{
    const getAllProducts = async () =>{
      const { data } = await request.get("/product")
      setProducts(data);
    }
    getAllProducts();
  }, [])

  return (
    <div className='flex flex-col items-center justify-center'>
      <div className='relative w-full max-w-4xl'>
        <img src="https://media.giphy.com/media/nXClOAbtZIIfu/giphy-downsized-large.gif" alt="mueble" className='w-full p-6 blur-xs'/>
        <p className='absolute inset-0 flex items-center justify-center font-bold text-4xl'>¡DESCUBRE NUESTRAS OFERTAS!</p>
      </div>
      <div className='grid grid-cols-4 space-x-20 p-5'>
        {products.map(product => (
          <Link to ={`/product/${product.id}`} key={product.id}>
            <ProductCard product={product} key={product.id}/>
          </Link>
        ))}
      </div>
    </div>
  )
}

export default MainPage