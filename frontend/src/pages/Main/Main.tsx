import { useTitle } from '../../hooks';
import { Page } from '../../typings/page';

interface MainPageProps extends Page {}

export function MainPage({ title }: MainPageProps) {
  useTitle(title);

  return <p>Main page</p>;
}
