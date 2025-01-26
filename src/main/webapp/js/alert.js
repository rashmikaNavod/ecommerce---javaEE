const alertType = '<%= request.getAttribute("alert") %>';

if (alertType === 'success') {
    Swal.fire({
        icon: 'success',
        title: 'Product Saved!',
        text: 'Your product has been successfully added.',
    });
} else if (alertType === 'image-resolution-error') {
    Swal.fire({
        icon: 'error',
        title: 'Invalid Image',
        text: 'The image must be 800x800 resolution.',
    });
} else if (alertType === 'db-error') {
    Swal.fire({
        icon: 'error',
        title: 'Database Error',
        text: 'There was an error saving your product. Please try again.',
    });
} else if (alertType === 'server-error') {
    Swal.fire({
        icon: 'error',
        title: 'Server Error',
        text: 'An unexpected error occurred. Please try again later.',
    });
}