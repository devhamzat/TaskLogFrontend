import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Landing from "./pages/Landing";
import Register from "./pages/Auth/Register";
import Login from "./pages/Auth/Login";
import AccountActivation from "./pages/Auth/AccountActivation";
import ProtectedRoutes from "./components/ProtectedRoutes";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route exact path="/" element={<Landing />} />
          <Route exact path="/register" element={<Register/>}/>
          <Route exact path="/sign_in" element={<Login />} />
          <Route exact path="/activate-account" element= {<AccountActivation />}/>
          {/* <ProtectedRoutes path="/create-account" element={<CreateAccount />} /> */}
          {/* <Route path="/create-account" element={<ProtectedRoutes element={CreateAccount} />} /> */}
        </Routes>
      </Router>
    </>
  );
}

export default App;
