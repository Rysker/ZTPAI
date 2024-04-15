import LoginPage from "./pages/LoginPage";
import Vehicles from "./pages/Vehicles";
import VehicleDetails from "./pages/VehicleDetails";
import {BrowserRouter, Routes, Route} from "react-router-dom";
import "./App.css"
import ModelKit from "./pages/ModelKit";
import RegisterPage from "./pages/RegisterPage";
import MyCollection from "./pages/MyCollection";
import Profile from "./pages/Profile";
import MainPage from "./pages/MainPage";
import HomePage from "./pages/HomePage";
import AdminPage from "./pages/AdminPage";
import Reports from "./pages/Reports";

function App ()
{
    return (
        <BrowserRouter>
            <Routes>
                <Route path ='/' element = { <MainPage/>  }></Route>
                <Route path ='/login' element = { <LoginPage/> }></Route>
                <Route path ='/register' element = { <RegisterPage/> }></Route>
                <Route path ='/home' element = { <HomePage/>  }></Route>
                <Route path ='/vehicles' element = { <Vehicles/> }></Route>
                <Route path ='/vehicles/:id' element={<VehicleDetails />} />
                <Route path ='/vehicles/:id/:id' element={<ModelKit/>} />
                <Route path ='/collection/:id' element={<MyCollection/>} />
                <Route path ='/profile/:id' element={<Profile/>} />
                <Route path ='/admin' element={<AdminPage/>} />
                <Route path ='/admin/reports' element={<Reports/>} />
            </Routes>
        </BrowserRouter>
    )
}

export default App;