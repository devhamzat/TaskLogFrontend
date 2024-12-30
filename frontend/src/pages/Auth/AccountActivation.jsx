import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const AccountActivation = () => {
    const [activationCode, setActivationCode] = useState(new Array(6).fill(''));
    const [activationStatus, setActivationStatus] = useState(null);
    const navigate = useNavigate();
  

    const handleInputChange = (index, event) => {
        const value = event.target.value;
        if (/^[0-9]$/.test(value) || value === '') {
            const newActivationCode = [...activationCode];
            newActivationCode[index] = value;
            setActivationCode(newActivationCode);
        }
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const code = activationCode.join('');
        try {
            const response = await axios.get(`${process.env.REACT_APP_BACKEND_BASE_URL}/auth/activate_account?token=${code}`);
            setActivationStatus(response.data.message);
            navigate('/sign_in');
            
        } catch (error) {
            if (error.response && error.response.data) {
                setActivationStatus(error.response.data.message);
            } else {
                setActivationStatus('Activation failed. Please try again.');
            }
        }
    };

    return (
        <section  className="bg-white-50 dark:bg-white-100 flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0">
             <div className="w-full rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:border-gray-700 bg-transparent backdrop-blur p-6 space-y-4 md:space-y-6 sm:p-8" >
            <h1 className="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white text-center">Account Activation</h1>
            <form onSubmit={handleSubmit} className="space-y-4 md:space-y-6 justify-center text-center">
                {activationCode.map((digit, index) => (
                    <input
                        key={index}
                        type='tel'
                        value={digit}
                        onChange={(event) => handleInputChange(index, event)}
                        maxLength="1"
                         className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 w-8 p-2.5 py-2 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 mx-2 text-center"
                    />
                     
                ))}
                <button type="submit"
                className="w-full text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800"
                disabled={activationCode.every(digit => digit === '')}
                >Submit</button>
            </form>
            {activationStatus && <p className="text-red-400">{activationStatus}</p>}
        </div>
        </section>
    );
};

export default AccountActivation;