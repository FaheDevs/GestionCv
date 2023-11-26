export async function  retrievePersonDetails(email) {
    try {
        // Retrieve Bearer token from local storage
        const bearerToken = localStorage.getItem('authToken');

        if (!bearerToken) {
            throw new Error('Bearer token not found in local storage');
        }

        const response = await fetch(`/api/v1/management/person/details?email=${encodeURIComponent(email)}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${bearerToken}`,
                // Add other headers as needed
            },
        });

        if (!response.ok) {
            throw new Error('Failed to retrieve person details');
        }

        const Details = await response.json();
        console.log('Person Details:', Details);
        return Details;

        // Process the personDetails as needed

    } catch (error) {
        console.error('Error retrieving person details:', error.message);
        // Handle the error (e.g., show an error message to the user)
    }
}

export async function createCV(email) {
    const apiUrl = `/api/v1/management/cvs/create?email=${encodeURIComponent(email)}`;

    const bearerToken = localStorage.getItem('authToken');

    if (!bearerToken) {
        throw new Error('Bearer token not found in local storage');
    }

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${bearerToken}`, // Include your auth token here
            },
            body: JSON.stringify({ email }),
        });

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        const cvId = await response.json();
        return cvId;
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to create CV');
    }
}

export async function updatePersonDetails(updatedPersonData){
    const apiUrl = `/api/v1/management/person/update`;

    const bearerToken = localStorage.getItem('authToken');

    if (!bearerToken) {
        throw new Error('Bearer token not found in local storage');
    }

    try {
        const response = await fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${bearerToken}`, // Include your auth token here
            },
            body: JSON.stringify(updatedPersonData)
        });

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        return await response.json();
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to update person');
    }

}

export async function addExperience(cvId,newExperience){
    const apiUrl = `/api/v1/experiences/${cvId}`;

    const bearerToken = localStorage.getItem('authToken');

    if (!bearerToken) {
        throw new Error('Bearer token not found in local storage');
    }

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${bearerToken}`,
            },
            body: JSON.stringify(newExperience)
        });

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        return await response.json();
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to add experience');
    }

}

export async function updateExperienceDetails(experienceID, updatedExperienceData){
    const apiUrl = `/api/v1/experiences/${experienceID}`;

    const bearerToken = localStorage.getItem('authToken');

    if (!bearerToken) {
        throw new Error('Bearer token not found in local storage');
    }

    try {
        const response = await fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${bearerToken}`,
            },
            body: JSON.stringify(updatedExperienceData)
        });

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        return await response.json();
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to update experience');
    }

}

export async function deleteExperience(experienceID){
    const apiUrl = `/api/v1/experiences/${experienceID}`;

    const bearerToken = localStorage.getItem('authToken');

    if (!bearerToken) {
        throw new Error('Bearer token not found in local storage');
    }

    try {
        const response = await fetch(apiUrl, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${bearerToken}`,
            },
        });

        if (response.status === 204) {
            // No content, return null or some other indicator
            return null;
        }

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        return await response.json();
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to delete experience');
    }

}

export async function registerPerson(newPerson){
    const apiUrl = `/api/v1/auth/register`;


    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(newPerson)
        });

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        return await response.json();
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to add new person ');
    }

}

export async function logout(){
    const apiUrl = `/api/v1/auth/logout`;

    try {
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (response.status === 200) {
            // No content, return null or some other indicator
            return null;
        }

        if (!response.ok) {
            // Handle error response
            const errorData = await response.json();
            throw new Error(`API error: ${errorData.message}`);
        }

        return await response.json();
    } catch (error) {
        // Handle fetch error
        console.error('Fetch error:', error.message);
        throw new Error('Failed to logout ');
    }

}