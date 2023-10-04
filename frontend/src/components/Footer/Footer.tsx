import { CONTACT_URL, GITHUB_REPO_URL, LICENSE_URL } from '@data/constants';

export function Footer() {
  return (
    <div className='border-gray-200 bg-gray-50 dark:bg-gray-800 dark:border-gray-700'>
      <div className='mx-auto max-w-screen-xl mt-4 bottom-0 w-full p-4 bg-white border-t border-gray-200 shadow md:flex md:items-center md:justify-between dark:bg-gray-800 dark:border-gray-600'>
        <span className='text-sm text-gray-500 sm:text-center dark:text-gray-400'>© 2023 Mock Api Hub</span>
        <ul className='flex flex-wrap items-center mt-3 text-sm font-medium text-gray-500 dark:text-gray-400 sm:mt-0'>
          <li>
            <a href={LICENSE_URL} className='mr-4 hover:underline md:mr-6'>
              License
            </a>
          </li>
          <li>
            <a href={CONTACT_URL} className='mr-4 hover:underline md:mr-6'>
              Contact
            </a>
          </li>
          <li>
            <a href={GITHUB_REPO_URL} className='hover:underline'>
              Github
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
}
