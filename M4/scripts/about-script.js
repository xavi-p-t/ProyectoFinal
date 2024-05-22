document.addEventListener("DOMContentLoaded", function() {
    function applyAnimation(element, animationClass, callback) {
        element.classList.add(animationClass);
        element.addEventListener('animationend', function handler() {
            element.classList.remove(animationClass);
            element.removeEventListener('animationend', handler);
            if (callback) callback();
        });
    }

    function verTrabajador(workerClass) {
        const allWorkers = document.querySelector('.all_workers');
        applyAnimation(allWorkers, 'compress', () => {
            allWorkers.style.display = 'none';
            const worker = document.querySelector(workerClass);
            worker.style.display = 'block';
            applyAnimation(worker, 'expand');
        });
    }

    function volver(workerClass) {
        const worker = document.querySelector(workerClass);
        applyAnimation(worker, 'compress', () => {
            worker.style.display = 'none';
            const allWorkers = document.querySelector('.all_workers');
            allWorkers.style.display = 'flex';
            applyAnimation(allWorkers, 'expand');
        });
    }

    document.querySelector('.press a[href="#worker1"]').addEventListener('click', function(event) {
        event.preventDefault();
        verTrabajador('.worker1');
    });

    document.querySelector('.press a[href="#worker2"]').addEventListener('click', function(event) {
        event.preventDefault();
        verTrabajador('.worker2');
    });

    document.querySelector('.press a[href="#worker3"]').addEventListener('click', function(event) {
        event.preventDefault();
        verTrabajador('.worker3');
    });

    document.querySelector('.worker1 .volver button').addEventListener('click', function() {
        volver('.worker1');
    });

    document.querySelector('.worker2 .volver button').addEventListener('click', function() {
        volver('.worker2');
    });

    document.querySelector('.worker3 .volver button').addEventListener('click', function() {
        volver('.worker3');
    });
});