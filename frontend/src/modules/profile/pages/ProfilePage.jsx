import React, { useEffect, useState } from 'react'
import { request } from '@shared'

function ProfilePage() {
    const [user, setUser] = useState({
        name: "",
        paternalSurname: "",
        maternalSurname: "",
        phoneNumber: "",
        street: "",
        houseNumber: "",
        state: "",
        municipality: "",
        zipCode: "",
        neighborhood: ""
    });
    const [refresh, setRefresh] = useState(false);

    useEffect(()=>{
        const getUserInfo = async () =>{
            const { data } = await request.get("/user/me")
            setUser({
                name: data.name,
                paternalSurname: data.paternalSurname,
                maternalSurname: data.maternalSurname,
                phoneNumber: data.phoneNumber.phoneNumber,
                street: data.address.street,
                houseNumber: data.address.houseNumber,
                state: data.address.state.name,
                municipality: data.address.municipality.name,
                zipCode: data.address.zipCode.zipCode,
                neighborhood: data.address.neighborhood.name
            });
        }
        getUserInfo();
    }, [refresh])

    const handleChange = (e) => {
        const { name, value } = e.target;
        // Add the new values to the user object.
        setUser(u => ({
            ...u,
            [name]: value
        }));
    }

    const onSubmit = async (e) =>{
        // We don´t want it to refresh the page.
        e.preventDefault();

        // Calling the API to update the user
        await request.put("/user", user)

        // Refreshing the page to see the results
        setRefresh(prev => !prev);
    }

    return (
        <div className=''>
            <form onSubmit={onSubmit} className='text-xl'>
                <h2 className='text-2xl font-bold'>DATOS PERSONALES</h2>

                <h3 className='font-semibold'>Nombre:</h3>
                <input type="text" name = "name" value={user.name} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Apellido paterno</h3>
                <input type="text" name = "paternalSurname" value={user.paternalSurname} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Apellido materno</h3>
                <input type="text" name = "maternalSurname" value={user.maternalSurname} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Número de teléfono</h3>
                <input type="text" name = "phoneNumber" value={user.phoneNumber} className='bg-black/10'
                onChange={handleChange} required minLength={10} maxLength={10}/>

                <h2 className='text-2xl font-bold'>DATOS DE ENVÍO</h2>

                <h3 className='font-semibold'>Estado</h3>
                <input type="text" name = "state" value={user.state} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Municipio</h3>
                <input type="text" name = "municipality" value={user.municipality} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Código Postal</h3>
                <input type="text" name = "zipCode" value={user.zipCode} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Colonia</h3>
                <input type="text" name = "neighborhood" value={user.neighborhood} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Calle</h3>
                <input type="text" name = "street" value={user.street} className='bg-black/10' onChange={handleChange} required/>

                <h3 className='font-semibold'>Número Exterior</h3>
                <input type="text" name = "houseNumber" value={user.houseNumber} className='bg-black/10' onChange={handleChange} required/>

                <button type='submit' className='flex bg-dark-blue text-white p-2 rounded-xl font-semibold mt-4 cursor-pointer'>Modificar mis datos</button>
            </form>
        </div>
    )
}

export default ProfilePage