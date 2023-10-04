import ReactDOM from 'react-dom/client';
import { App } from '@/App';
import '@/index.css';
import { BrowserRouter } from 'react-router-dom';
import { ErrorBoundary } from '@components/ErrorBoundary';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <ErrorBoundary>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </ErrorBoundary>
);
