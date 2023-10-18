import Logo from '@assets/logo.svg';
import { Footer } from '@components/Footer';
import { Navbar } from '@components/Navbar';
import { ENTITIES } from '@data/constants';
import { useTitle } from '@hooks/useTitle';
import { Page } from '@typings/page';
import { Entity } from '@pages/Home/Entity';

interface HomeProps extends Page {}

export function Home({ title }: HomeProps) {
  useTitle(title);

  return (
    <> 
      <Navbar />
      <div className='min-h-[calc(100vh-153px)] container mx-auto px-16 mt-5 text-center'>
        <div className='mx-auto'>
          <img src={Logo} alt='Mock Api Hub' className='lg:w-1/4 md:w-1/4 sm:1/3 mx-auto' />
          <h1 className='text-6xl font-bold'>Mock Api Hub</h1>
          <p className='text-1xl mt-1'>🪄 A cost-free simulated API designed for the purpose of facilitating testing</p>
          <div className='mt-6 grid grid-cols-2 sm:grid-cols-1 md:grid-cols-2 lg:grid-cols-3  gap-4'>
            {ENTITIES.map(entity => (
              <Entity name={entity} />
            ))}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
