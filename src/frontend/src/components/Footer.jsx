import React from "react";
import { Linkedin,Twitter } from 'lucide-react';

export default function Footer() {
  return (
    <footer className="mt-20 border-t py-10 border-neutral-700">
      <div className="grid grid-cols-2 lg:grid-cols-3 gap-4">
        <div>
          <p>Built with ❤️</p>
          <p>Copyright © 2024 <a href="">devhamzat</a></p>
        </div>
      </div>
    </footer>
  );
}
