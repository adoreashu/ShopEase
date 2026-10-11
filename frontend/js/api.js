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

function getProductImage(product) {
    if (product.imageUrl && product.imageUrl.trim().length > 0) {
        return product.imageUrl;
    }
    const name = (product.name || '').toLowerCase();
    if (name.includes('headphone')) {
        return 'https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=600&auto=format&fit=crop&q=80';
    } else if (name.includes('phone')) {
        return 'https://images.unsplash.com/photo-1511707171634-5f897ff02560?w=600&auto=format&fit=crop&q=80';
    } else if (name.includes('laptop')) {
        return 'https://images.unsplash.com/photo-1496181133206-80ce9b88a853?w=600&auto=format&fit=crop&q=80';
    } else if (name.includes('watch')) {
        return 'https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=600&auto=format&fit=crop&q=80';
    } else if (name.includes('keyboard')) {
        return 'https://images.unsplash.com/photo-1587829741301-dc798b83add3?w=600&auto=format&fit=crop&q=80';
    } else if (name.includes('monitor')) {
        return 'https://images.unsplash.com/photo-1527443224154-c4a3942d3acf?w=600&auto=format&fit=crop&q=80';
    }
    return 'https://images.unsplash.com/photo-1526170375885-4d8ecf77b99f?w=600&auto=format&fit=crop&q=80';
}
