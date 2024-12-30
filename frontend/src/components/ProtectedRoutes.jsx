// ProtectedRoutes.jsx
import React from "react";
import { Navigate } from "react-router-dom";
import Cookies from "universal-cookie";

const cookies = new Cookies();

export default function ProtectedRoutes({ children }) {
    const token = cookies.get("token");
    
    if (!token) {
        return <Navigate to="/sign_in" replace />;
    }

    return children;
}

