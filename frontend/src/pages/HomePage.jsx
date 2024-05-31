import { TbTank } from "react-icons/tb";
import { FaFighterJet } from "react-icons/fa";
import { GiBattleship } from "react-icons/gi";
import '../App.css';
import '../styles/HomePage.css';
import  "../styles/VehicleDetails.css";
import "../styles/Profile.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Webpage from "../components/Webpage";

function Profile()
{
    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar></DefaultNavbar>
                    <div className="content-space-home">
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Tanks">
                                <TbTank></TbTank>
                            </a>
                        </div>
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Planes">
                                <FaFighterJet></FaFighterJet>
                            </a>
                        </div>
                        <div className="vehicle-pick">
                            <a href="/vehicles?type=Ships">
                                <GiBattleship></GiBattleship>
                            </a>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}
export default Profile;