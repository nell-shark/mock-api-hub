import { Route, Routes } from 'react-router-dom';
import { MainPage } from './pages/Main';
import { NotFoundPage } from './pages/NotFound';

export function App() {
  return (
    <Routes>
      <Route path='/' element={<MainPage title={'Mock Api Hub'} />} />
      <Route path='*' element={<NotFoundPage title={'Not Found'} />} />
    </Routes>
  );
}
