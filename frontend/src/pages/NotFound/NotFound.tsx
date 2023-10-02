import { useTitle } from '../../hooks';
import { Page } from '../../typings/page';
import BackArrow from '../../assets/back-arrow.svg';
import NotFound from '../../assets/not-found-404.svg';
import { Link } from 'react-router-dom';

interface NotFoundPageProps extends Page {}

export function NotFoundPage({ title }: NotFoundPageProps) {
  useTitle(title);

  return (
    <div className='w-full h-screen flex flex-col items-center justify-center'>
      <img src={NotFound} alt='404' className='w-1/2 md:1/3 lg:w-1/4 text-blue-600' />
      <div className='flex flex-col items-center justify-center'>
        <p className='text-3xl md:text-4xl lg:text-5xl text-gray-800 mt-12'>Page Not Found</p>
        <p className='md:text-lg lg:text-xl text-gray-600 mt-8'>
          Sorry, the page you are looking for could not be found.
        </p>
        <Link
          to='/'
          className='flex items-center space-x-2 bg-blue-600 hover:bg-blue-700 text-gray-100 px-4 py-2 mt-12 rounded transition duration-150'
        >
          <img src={BackArrow} alt='404' className='h-5 w-5' />
          <span>Return Home</span>
        </Link>
      </div>
    </div>
  );
}
