interface EntityProps {
    name: string
}

export function Entity({name}: EntityProps) {
  return (
    <a href={`/${name}`} className='font-bold text-blue-600 dark:text-blue-500 hover:underline'>
      /{name}
    </a>
  );
}
