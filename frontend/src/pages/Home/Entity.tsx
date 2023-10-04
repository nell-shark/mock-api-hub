interface EntityProps {
    name: string
}

export function Entity({name}: EntityProps) {
  return (
    <a href={`/api/v1/${name}`} className='font-bold text-blue-600 dark:text-blue-500 hover:underline'>
      {name}
    </a>
  );
}
