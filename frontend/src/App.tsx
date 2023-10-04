import { Route, Routes } from 'react-router-dom';
import { Home } from './pages/Home';
import { NotFound } from './pages/NotFound';

export function App() {
  return (
    <Routes>
      <Route path='/' element={<Home title={'Mock Api Hub'} />} />
      <Route path='*' element={<NotFound title={'Not Found'} />} />
    </Routes>
  );
}
