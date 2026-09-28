const API_BASE_URL = 'http://localhost:8080/api';

async function apiCall(endpoint, method = 'GET', data = null) {
    const token = localStorage.getItem('token');
    const headers = {
        'Content-Type': 'application/json'
    };
    if (token) {
        headers['Authorization'] = 'Bearer ' + token;
    }
    
    const config = {
        method,
        headers
    };
    if (data) {
        config.body = JSON.stringify(data);
    }
    
    const response = await fetch(API_BASE_URL + endpoint, config);
    if (!response.ok) {
        const error = await response.text();
        throw new Error(error || response.statusText);
    }
    const text = await response.text();
    return text ? JSON.parse(text) : {};
}
