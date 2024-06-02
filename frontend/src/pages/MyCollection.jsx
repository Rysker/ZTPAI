import React, {useState, useEffect} from 'react';
import '../App.css';
import  "../styles/VehicleDetails.css";
import {DefaultNavbar} from "../components/DefaultNavbar";
import Filter from "../components/Filter";
import VehicleKit from "../components/VehicleKit";
import { useParams } from 'react-router-dom';
import axios from "axios";
import Webpage from "../components/Webpage";

const API_ENDPOINT = process.env.REACT_APP_API_ENDPOINT;

const optionMap = {
    "Public" : "true",
    "Hidden" : "false",
    "Owned" : "Owned",
    "Finished" : "Finished"
};

const titleMap = {
    "visibility" : "isPublic",
    "progress": "status"
};

const guestFilters = [{
        "title": "progress",
        "options": ["Owned", "Finished"]
}]

function MyCollection({setError, setSuccess})
{
    const { profileName } = useParams();
    const [collection, setCollection] = useState([]);
    const [sameUser, setSameUser] = useState(false);
    const [filters, setFilters] = useState([]);
    const [selectedFilters, setSelectedFilters] = useState([]);

    useEffect(() =>
    {
        const fetchCollection = async () =>
        {
            try
            {
                const response = await axios.get(`${API_ENDPOINT}/api/v1/collection/${profileName}`);
                const filterResponse = await axios.get(`${API_ENDPOINT}/api/v1/filters/collection`);
                setCollection(response.data.kits);
                setSameUser(response.data.isSameUser === "true");
                setFilters(filterResponse.data);
            }
            catch (error)
            {
                console.error('Error!');
            }
        };

        fetchCollection();
    }, [profileName]);

    const changeObserved = async (id) =>
    {
        const response = await axios.post(`${API_ENDPOINT}/api/v1/watchlist/change/${id}`);
        if(response.status !== 200)
            setError('Failed to change observed status.');
    };

    const finishCollectible = async (id) =>
    {
        setCollection(prevCollection =>
            prevCollection.map(kit =>
                kit.id === id ? { ...kit, status: "Finished", completionDate: new Date().toISOString().split('T')[0] } : kit
            )
        );
    }

    const deleteCollectible = async (id) =>
    {
        setCollection(prevCollection => prevCollection.filter(item => item.id !== id));
    };

    const handleCheckboxChange = (filterTitle, option) =>
    {
        const isSelected = selectedFilters.some(filter => filter.title === filterTitle && filter.option === option);
        if (isSelected)
            setSelectedFilters(selectedFilters.filter(filter => !(filter.title === filterTitle && filter.option === option)));
        else
            setSelectedFilters([...selectedFilters, { title: filterTitle, option }]);
    };

    const filteredKits = () =>
    {
        if (selectedFilters.length === 0)
            return collection;
        else
        {
            return collection.filter(kit =>
            {
                const filtersByTitle = {};
                selectedFilters.forEach(filter =>
                {
                    if (!filtersByTitle[titleMap[filter.title]])
                        filtersByTitle[titleMap[filter.title]] = [];
                    filtersByTitle[titleMap[filter.title]].push(optionMap[filter.option]);
                });

                return Object.entries(filtersByTitle).every(([title, options]) =>
                {
                    return options.some(option =>
                    {
                        if(title === "isPublic")
                            return kit[title] === option;
                        else
                            return kit[title.toLowerCase()] === option;
                    });
                });
            });
        }
    };

    return (
        <Webpage className={"webpage"}>
            {({ setError, setSuccess }) => (
                <>
                    <DefaultNavbar></DefaultNavbar>
                    <div className="content-space">
                        <div className="content-space-info">
                            <div className="content-space-info-filter">
                                <Filter title= "Collection" filters={sameUser ? filters : guestFilters} onCheckboxChange={handleCheckboxChange} />
                            </div>
                            <div className="content-space-info-display-row">
                                {filteredKits().map((kit) => (
                                    <VehicleKit key={kit.collectibleId}
                                                kit={kit}
                                                showCollection={true}
                                                changeObserved={changeObserved}
                                                editable={sameUser}
                                                deleteCollectible={deleteCollectible}
                                                finishCollectible={finishCollectible}
                                                setError={setError}
                                                setSuccess={setSuccess}
                                    />
                                ))}
                            </div>
                        </div>
                    </div>
                </>
            )}
        </Webpage>
    )
}
export default MyCollection;